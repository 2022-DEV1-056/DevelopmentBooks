import React, { useEffect, useState } from "react";
import "../styles/dashboard.css";
import axios from "axios";

const Dashboard = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/developmentbooks/getBooks")
      .then((response) => {
        setBooks(response.data);
      });
  }, []);
  return (
    <div className="container">
      <div className="header">
        <h1 className="title">Development Books</h1>
      </div>
      <div className="content">
        <div className="products">
          {books.map((book) => {
            const { title, author, imageUrl, price, id, year } = book;
            return (
              <div className="product" key={id}>
                <div className="product-image">
                  <img src={imageUrl} alt={title} />
                </div>
                <p className="product-title">{title}</p>
                <p className="author">
                  By {author} ({year})
                </p>
                <p className="product-price">{price}</p>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
