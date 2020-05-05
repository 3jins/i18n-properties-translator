import React, {useState, useEffect, useContext} from 'react';
import axios from 'axios';
import _ from 'lodash';
import apiUrlMap from '../../../constant/apiUrlMap';
import languageTypeMap from '../../../constant/languageTypeMap';
import PropertiesContentContext from '../../../context/PropertiesContentContext';
import './PropertiesContentInput.css';

export default () => {
  const {baseUrl, propertiesTranslation} = apiUrlMap;
  const {propertiesTranslationResponse, setPropertiesTranslationResponse} = useContext(PropertiesContentContext);
  const [propertiesContentText, setPropertiesContentText] = useState();

  const convertLanguageTypeMapToCheckedStateMap = () => {
    const checkedStateMap = Object.assign({}, languageTypeMap);
    Object.keys(checkedStateMap).forEach(key => checkedStateMap[key] = false);
    return checkedStateMap;
  };

  const [checkAll, setCheckAll] = useState(false);
  const [checkedStateMap, setCheckedStateMap] = useState(convertLanguageTypeMapToCheckedStateMap());

  const renderCheckboxList = () => (
    <div className="target-language-type-checkbox-list">
      <div className="target-language-type-checkbox all-checkbox">
        <input value="all" type="checkbox" checked={checkAll} onChange={handleClickCheckAll}/>
        <span>ALL</span>
      </div>
      {Object.keys(checkedStateMap).map((key) => (
        <div key={key} className="target-language-type-checkbox">
          <input value={key} type="checkbox" checked={checkedStateMap[key]}
                 onChange={handleClickLanguageTypeCheckbox}/>
          <span>{languageTypeMap[key]}</span>
        </div>
      ))}
    </div>
  );

  const handleClickCheckAll = (event) => {
    const copiedCheckedStateMap = {};
    Object.assign(copiedCheckedStateMap, checkedStateMap);
    Object.keys(checkedStateMap)
      .forEach(key => copiedCheckedStateMap[key] = event.target.checked);
    setCheckedStateMap(copiedCheckedStateMap);
    setCheckAll(event.target.checked);
  };

  const handleClickLanguageTypeCheckbox = (event) => {
    const copiedCheckedStateMap = {};
    Object.assign(copiedCheckedStateMap, checkedStateMap);
    const checkedKeys = Object.keys(checkedStateMap)
      .filter(key => key === event.target.value);
    checkedKeys.forEach(key => copiedCheckedStateMap[key] = event.target.checked);
    setCheckedStateMap(copiedCheckedStateMap);
  };

  const handleSubmitPropertiesContent = (event) => {
    event.preventDefault();
    if (Object.values(checkedStateMap).filter(checkedState => checkedState).length === 0) {
      return alert('최소 1개의 언어는 선택되어 있어야 합니다.');
    }
    if (_.isEmpty(propertiesContentText)) {
      return alert('properties 내용을 붙여넣어주세요.');
    }
    axios
      .post([baseUrl, propertiesTranslation].join(''), {
        propertiesRawContent: propertiesContentText,
        sourceLanguageTypeCode: 'ko', // TODO: Use language detection or selectbox and limit impossible cases - https://developers.naver.com/docs/papago/papago-nmt-api-reference.md#%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0
        targetLanguageTypeCodeList: Object.keys(checkedStateMap).filter(key => checkedStateMap[key]),
      })
      .then(res => setPropertiesTranslationResponse(res.data))
      .catch((err) => {
        if (!err.hasOwnProperty('response') || _.isEmpty(err.response)) {
          alert('서버와의 통신 중 장애가 발생했습니다.');
          return;
        }
        const {response: {data: {message: errorMessage}}} = err;
        if (errorMessage.includes('[')) {
          const sliceFrom = errorMessage.indexOf('['); // index of first '['
          const errorMessageObjectList = JSON.parse(errorMessage.slice(sliceFrom));
          const errorMessageList = errorMessageObjectList
            .filter(errorMessageObject => errorMessageObject.hasOwnProperty('errorMessage'))
            .map(errorMessageObject => errorMessageObject.errorMessage);
          alert(errorMessageList.join('\n\n'));
          const errorMessageGoingWrongList = errorMessageObjectList
            .filter(errorMessageObject => !errorMessageObject.hasOwnProperty('errorMessage'))
            .map(errorMessageGoingWrongObject => errorMessageGoingWrongObject.errorMessage);
          if (errorMessageGoingWrongList.length === 0) {
            return;
          }
        }
        alert('내부 서버 장애가 발생했습니다.');
        console.error(errorMessage);
      });
  };

  const handleChangePropertiesContent = (event) => setPropertiesContentText(event.target.value);

  const hintMessage = [
    'Copy i18n property content(Korean) here.',
    '',
    '-------- example --------',
    '',
    'title=\'열정과 냉정 사이\'를 읽고',
    'content=참 재미있었다.',
    'post_type=독후감',
  ].join('\n');

  return (
    <div className="PropertiesContentInput">
      <form onSubmit={handleSubmitPropertiesContent}>
        <textarea value={propertiesContentText} placeholder={hintMessage} onChange={handleChangePropertiesContent}/>
        {renderCheckboxList()}
        <input className="submit-button" type="submit" value="CONVERT"/>
      </form>
    </div>
  );
}
