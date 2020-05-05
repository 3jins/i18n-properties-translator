import React from 'react';
import './Footer.css';

export default () => (
  <div className="Footer">
    <hr className="footer-start-line"/>
    <div className="developer-introduction">
      <table>
        <tbody>
          <tr>
            <th>만든이:</th>
            <td>전세진</td>
          </tr>
          <tr>
            <th>깃허브:</th>
            <td><a href="https://github.com/3jins">https://github.com/3jins</a></td>
          </tr>
          <tr>
            <th>블로그:</th>
            <td><a href="https://enhanced.kr">https://enhanced.kr</a></td>
          </tr>
          <tr>
            <th>고양이:</th>
            <td><a href="https://www.youtube.com/watch?v=PjhU7EP030M">는 귀엽다.</a></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
);
