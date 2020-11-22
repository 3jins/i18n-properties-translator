# i18n-properties-translator
Message processing for i18n(internalization) is convenient. However, it's not so easy to write down message scripts for every language that your web application wants to support. `i18n-properties-translator` will automatically make scripts for all other language only if you make one in Korean. 

## Demo
<img src="./documentation/demo.gif"></img>

## Features
- Language Support
  - [x] Korean -> other languages
  - [ ] English -> other languages
  - [ ] any languages -> other languages
- Translator Support
  - [x] [Papago](https://developers.naver.com/docs/papago/README.md)
  - [ ] [Google Translate](https://cloud.google.com/translate)
- Configuration File Type Support
  - [x] properties
  - [ ] yaml, yml
  - [ ] json
  - [ ] xml
  - [ ] javascript
  
## Notification
- Token on `application.yml` belongs to my Naver account, and it has [daily limit](https://developers.naver.com/products/intro/faq/#q--%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%98%A4%ED%94%88api%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%A0-%EB%95%8C-%EC%A3%BC%EC%9D%98%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EC%A0%9C%ED%95%9C-%EC%82%AC%ED%95%AD%EC%97%90%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%B4-%EC%9E%88%EB%82%98%EC%9A%94?) for using Papago translation API. So I suggest you to use your own token for large amount of translation.
