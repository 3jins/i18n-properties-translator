import { createContext } from 'react';

export default createContext({
  propertiesTranslationResponse: [],
  isLoading: false,
  setPropertiesTranslationResponse: () => {},
  setIsLoading: () => {},
});
