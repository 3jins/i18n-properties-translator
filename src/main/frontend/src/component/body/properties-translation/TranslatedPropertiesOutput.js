import React, {useContext} from 'react';
import propertiesContentContext from '../../../context/PropertiesContentContext';
import languageTypeMap from '../../../constant/languageTypeMap';
import './TranslatedPropertiesOutput.css';
import download from './download-2-16.png';

export default () => {
  const {propertiesTranslationResponse} = useContext(propertiesContentContext);

  const renderPropertiesTranslationResponse = () => (
    <div className="translation-output-list">
      {propertiesTranslationResponse
        .map(propertiesTranslation => (
          <div className="translation-output" key={propertiesTranslation.languageType}>
            <p className="language-type">
              {languageTypeMap[propertiesTranslation.languageType]}
              <a className="download-button"
                 download={`i18n_${propertiesTranslation.languageType}.properties`}
                 href={`data:application/octet-stream;,${formatPropertiesDataObjectForDownload(propertiesTranslation.translatedPropertiesData)}`}>
                <img src={download}/>
              </a>
            </p>
            <div className="translated-properties-content">
              {formatPropertiesDataObject(propertiesTranslation.translatedPropertiesData)}
            </div>
          </div>
        ))}
    </div>
  );

  const formatPropertiesDataObject = (propertiesDataObject) => Object.keys(propertiesDataObject)
    .map(propertyKey => [propertyKey, propertiesDataObject[propertyKey]].join('='))
    .map((formattedPropertyData, idx) => (
      <span className="formatted-property-data"
            key={['formattedPropertyData', idx].join('-')}>{formattedPropertyData}</span>
    ));

  const formatPropertiesDataObjectForDownload = (propertiesDataObject) => Object.keys(propertiesDataObject)
    .map(propertyKey => [propertyKey, propertiesDataObject[propertyKey]].join('='))
    .join('\n');

  return (
    <div className="TranslatedPropertiesOutput">
      {renderPropertiesTranslationResponse()}
    </div>
  );
};
