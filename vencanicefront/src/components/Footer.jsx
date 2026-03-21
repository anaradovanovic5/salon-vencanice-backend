import React from 'react'

export const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="footer-content">
          <div className="footer-section">
            <div className="brand">
              <div className="dot"></div>
              <span>Salon Venčanica</span>
            </div>
            <p className="footer-text">Pronađite savršenu venčanicu za vaš veliki dan.</p>
          </div>
          
          <div className="footer-section">
            <h4>Linkovi</h4>
            <ul className="footer-links">
              <li><a href="/o-nama" className="link">O nama</a></li>
              <li><a href="/uslovi" className="link">Uslovi</a></li>
              <li><a href="/privatnost" className="link">Privatnost</a></li>
            </ul>
          </div>
          
          <div className="footer-section">
            <h4>Kontakt</h4>
            <ul className="footer-links">
              <li>+381 11 123 456</li>
              <li>info@salonvencanica.rs</li>
              <li>Radno vreme: 09-20h</li>
            </ul>
          </div>
        </div>
        
        <div className="footer-bottom">
          <p>&copy; 2024 Salon Venčanica. Sva prava zadržana.</p>
          <p>Fakultet organizacionih nauka</p>
        </div>
      </div>
    </footer>
  )
}
