import React, {useContext} from 'react';
import propertiesContentContext from '../../../context/PropertiesContentContext';
import languageTypeMap from '../../../constant/languageTypeMap';
import './TranslatedPropertiesOutput.css';

export default () => {
  const {propertiesTranslationResponse} = useContext(propertiesContentContext);

  const renderPropertiesTranslationResponse = () => (
    <div className="translation-output-list">
      {propertiesTranslationResponse
        .map(propertiesTranslation => (
          <div className="translation-output" key={propertiesTranslation.languageType}>
            <p className="language-type">{languageTypeMap[propertiesTranslation.languageType]}</p>
            <div className="translated-properties-content">{formatPropertiesDataObject(propertiesTranslation.translatedPropertiesData)}</div>
          </div>
        ))}
    </div>
  );

  const formatPropertiesDataObject = (propertiesDataObject) => Object.keys(propertiesDataObject)
    .map(propertyKey => [propertyKey, propertiesDataObject[propertyKey]].join('='))
    .map((formattedPropertyData, idx) => (
      <span className="formatted-property-data" key={['formattedPropertyData', idx].join('-')}>{formattedPropertyData}</span>
    ));

  return (
    <div className="TranslatedPropertiesOutput">
      {renderPropertiesTranslationResponse()}
    </div>
  );
};
