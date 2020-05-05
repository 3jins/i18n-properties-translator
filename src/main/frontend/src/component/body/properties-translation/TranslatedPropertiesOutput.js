import React, {useContext} from 'react';
import propertiesContentContext from '../../../context/PropertiesContentContext';
import languageTypeMap from '../../../constant/languageTypeMap';

export default () => {
  const {propertiesTranslationResponse} = useContext(propertiesContentContext);

  const renderPropertiesTranslationResponse = () => (
    <div>
      {propertiesTranslationResponse
        .map(propertiesTranslation => (
          <div key={propertiesTranslation.languageType}>
            <p>{languageTypeMap[propertiesTranslation.languageType]}</p>
            {formatPropertiesDataObject(propertiesTranslation.translatedPropertiesData)}
          </div>
        ))}
    </div>
  );

  const formatPropertiesDataObject = (propertiesDataObject) => Object.keys(propertiesDataObject)
    .map(propertyKey => [propertyKey, propertiesDataObject[propertyKey]].join('='))
    .map((formattedPropertyData, idx) => (
      <span key={['formattedPropertyData', idx].join('-')}>{formattedPropertyData}</span>
    ));

  return (
    <div className="TranslatedPropertiesOutput">
      {renderPropertiesTranslationResponse()}
    </div>
  );
};
