import React, {useState} from 'react';
import PropertiesContentContext from './context/PropertiesContentContext';
import Header from './component/Header';
import PropertiesTranslation from './component/body/properties-translation/PropertiesTranslation';
import Footer from "./component/Footer";
import './App.css';

export default () => {
  const [propertiesTranslationResponse, setPropertiesTranslationResponse] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  return (
    <div className="App">
      <header className="app-header">
        <Header/>
      </header>
      <div className="app-body">
        <PropertiesContentContext.Provider value={{
          propertiesTranslationResponse,
          isLoading,
          setPropertiesTranslationResponse,
          setIsLoading,
        }}>
          <PropertiesTranslation/>
        </PropertiesContentContext.Provider>
      </div>
      <footer className="app-footer">
        <Footer/>
      </footer>
    </div>
  );
};
