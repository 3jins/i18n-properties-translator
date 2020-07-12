import React, {useContext} from 'react';
import _ from 'lodash';
import MoonLoader from 'react-spinners/MoonLoader';
import propertiesContentContext from '../../../context/PropertiesContentContext';
import languageTypeMap from '../../../constant/languageTypeMap';
import './TranslatedPropertiesOutput.css';
import download from './download.png';

export default () => {
  const {propertiesTranslationResponse, isLoading} = useContext(propertiesContentContext);

  const css = 'display: block; margin: 2rem auto;';

  const formatSinglePropertyData = (propertyData) => {
    if (_.isEmpty(propertyData.key)) {
      if (_.isEmpty(propertyData.comment)) {
        return '';
      }
      return ['#', propertyData.comment].join('');
    }
    const keyValue = [propertyData.key, propertyData.value].join('=');

    return _.isEmpty(propertyData.comment)
      ? keyValue
      : [keyValue, propertyData.comment].join(' #');
  };

  const formatPropertiesDataList = propertiesDataList => propertiesDataList
    .map(propertyData => formatSinglePropertyData(propertyData))
    .map((formattedPropertyData, idx) => _.isEmpty(formattedPropertyData)
      ? <br key={['formattedPropertyData', idx].join('-')}/>
      : (
        <span className="formatted-property-data" key={['formattedPropertyData', idx].join('-')}>
          {formattedPropertyData}
        </span>
      ));

  const formatPropertiesDataListForDownload = propertiesDataList => propertiesDataList
    .map(propertyData => formatSinglePropertyData(propertyData))
    .join('\n');

  const makeDownloadUrl = fileContent => URL.createObjectURL(new Blob([fileContent]));

  return (
    <div className="TranslatedPropertiesOutput">
      {isLoading ? (
        <MoonLoader color="#ff6060" css={css} />
      ) : (
        <div className="translation-output-list">
          {propertiesTranslationResponse
            .map(propertiesTranslation => (
              <div className="translation-output" key={propertiesTranslation.languageType}>
                <p className="language-type">
                  {languageTypeMap[propertiesTranslation.languageType]}
                  <a className="download-button"
                     download={`i18n_${propertiesTranslation.languageType}.properties`}
                     href={makeDownloadUrl(formatPropertiesDataListForDownload(propertiesTranslation.translatedPropertiesData))}>
                    <img src={download} alt="download icon"/>
                  </a>
                </p>
                <div className="translated-properties-content">
                  {formatPropertiesDataList(propertiesTranslation.translatedPropertiesData)}
                </div>
              </div>
            ))}
        </div>
      )}
    </div>
  );
};
