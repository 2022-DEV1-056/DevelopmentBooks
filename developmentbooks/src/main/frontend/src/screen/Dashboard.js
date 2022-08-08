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
            return (
              <p className="product" key={book.id}>
                {book.title}
              </p>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
