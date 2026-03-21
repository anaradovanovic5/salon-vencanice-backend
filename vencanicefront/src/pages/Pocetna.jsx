import React from 'react'
import '../css/Pocetna.css'
    

export const Pocetna = () => {
const categories = [
  { name: "A-line Venčanice", img: "https://images.unsplash.com/photo-1537832816519-689ad163238b?q=80&w=1200&auto=format&fit=crop"},
  { name: "Princeze Venčanice", img: "https://images.unsplash.com/photo-1591369822096-ffd140ec948f?q=80&w=1200&auto=format&fit=crop"},
  { name: "Mermaid Venčanice", img: "https://images.unsplash.com/photo-1583939003579-730e3918a45a?q=80&w=1200&auto=format&fit=crop"},
  { name: "Vintage Kolekcija", img: "https://images.unsplash.com/photo-1519657337289-077c8b0600e4?q=80&w=1200&auto=format&fit=crop"},
  { name: "Moderne Venčanice", img: "https://images.unsplash.com/photo-1595428774223-ef52624120d2?q=80&w=1200&auto=format&fit=crop"},
  { name: "Biser i Čipka", img: "https://images.unsplash.com/photo-1515169067864-12c7bcb61cd3?q=80&w=1200&auto=format&fit=crop"},
  { name: "Kratke Venčanice", img: "https://images.unsplash.com/photo-1585487000113-7007cbbdc92c?q=80&w=1200&auto=format&fit=crop"},
  { name: "Boja Badem", img: "https://images.unsplash.com/photo-1519996409144-56a88da48a5e?q=80&w=1200&auto=format&fit=crop"},
  { name: "Crvene Venčanice", img: "https://images.unsplash.com/photo-1581809548520-9288f0b9b2c9?q=80&w=1200&auto=format&fit=crop"},
  { name: "Bež Venčanice", img: "https://images.unsplash.com/photo-1519657337289-077c8b0600e4?q=80&w=1200&auto=format&fit=crop"},
  { name: "Duže Venčanice", img: "https://images.unsplash.com/photo-1585487000151-2a5031f4c0e5?q=80&w=1200&auto=format&fit=crop"},
  { name: "Kraljevske Venčanice", img: "https://images.unsplash.com/photo-1591369822096-ffd140ec948f?q=80&w=1200&auto=format&fit=crop"}
];



  return (
    <>
    <section className="hero">
  <div className="container hero-grid">
    <div className="hero-copy">
      <span className="pill">⭐ 4.8 od 500+ klijenata</span>
      <h1>Pronađi savršenu venčanicu za tvoj veliki dan.</h1>
      <p>Biraj između stotina dizajnerskih venčanica, rezerviši probu i ostvari popust na prvu kupovinu.</p>
      
      <form className="hero-search" onSubmit={(e) => e.preventDefault()}>
        <input placeholder="Unesi veličinu (npr. 38) ili model" />
        <button className="btn primary">Pogledaj kolekciju</button>
      </form>

      <div className="stats">
        <div><strong>200+</strong><span>Dizajnerskih venčanica</span></div>
        <div><strong>3-5</strong><span>Dana za dostavu</span></div>
        <div><strong>15%</strong><span>Popusta na prvu kupovinu</span></div>
      </div>
    </div>

    <div className="hero-art">
      <div className="hero-card big">
        <img 
          src="https://images.unsplash.com/photo-1537832816519-689ad163238b?q=80&w=1200&auto=format&fit=crop" 
          alt="Dizajnerska venčanica premium kvaliteta"
        />
        <div className="badge">Najtraženiji model</div>
      </div>
      <div className="hero-card small">
        <img 
          src="https://images.unsplash.com/photo-1595428774223-ef52624120d2?q=80&w=800&auto=format&fit=crop" 
          alt="Moderna venčanica sa applika"
        />
        <div className="badge">Nova kolekcija</div>
      </div>
    </div>
  </div>
</section>

    <section id="categories" className="section">
  <div className="container">
    <div className="section-head">
      <h2>Kategorije Venčanica</h2>
      <a className="link" href="#">Vidi sve</a>
    </div>
    <div className="grid cats">
      {categories.map((c) => (
        <a key={c.name} className="cat" href="#menu">
          <img src={c.img} alt={c.name} />
          <span>{c.name}</span>
        </a>
      ))}
    </div>
  </div>
</section>

<section id="locations" className="section">
  <div className="container">
    <div className="section-head">
      <h2>Naše Lokacije</h2>
      <p className="section-subtitle">Posetite nas u bilo kom od naših salona</p>
    </div>
    <div className="grid locations-grid">
      <div className="location-card">
        <div className="location-image">
          <img src="https://images.unsplash.com/photo-1560448204-603b3fc33ddc?q=80&w=600&auto=format&fit=crop" alt="Novi Beograd" />
        </div>
        <div className="location-info">
          <h3>Novi Beograd</h3>
          <p>Bulevar Mihajla Pupina 123</p>
          <p>Radno vreme: 09-20h</p>
          <a href="#" className="btn primary btn-block">Rezerviši termin</a>
        </div>
      </div>
      <div className="location-card">
        <div className="location-image">
          <img src="https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?q=80&w=600&auto=format&fit=crop" alt="Vračar" />
        </div>
        <div className="location-info">
          <h3>Vračar</h3>
          <p>Gradski Park 45</p>
          <p>Radno vreme: 09-20h</p>
          <a href="#" className="btn primary btn-block">Rezerviši termin</a>
        </div>
      </div>
      <div className="location-card">
        <div className="location-image">
          <img src="https://images.unsplash.com/photo-1513584684374-8bab748fbf90?q=80&w=600&auto=format&fit=crop" alt="Voždovac" />
        </div>
        <div className="location-info">
          <h3>Voždovac</h3>
          <p>Voždovačka 67</p>
          <p>Radno vreme: 09-20h</p>
          <a href="#" className="btn primary btn-block">Rezerviši termin</a>
        </div>
      </div>
    </div>
  </div>
</section>
</>
  )
}
