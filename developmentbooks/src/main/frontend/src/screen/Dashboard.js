import React, { useEffect, useState, Fragment } from "react";
import "../styles/dashboard.css";
import axios from "axios";
import Product from "../components/Product";

const Dashboard = () => {
  const [books, setBooks] = useState([]);
  const [discounts, setDiscounts] = useState({});

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/developmentbooks/getBooks")
      .then((response) => {
        setBooks(response.data);
      });
    axios
      .get("http://localhost:8080/api/developmentbooks/getDiscountDetails")
      .then((response) => {
        setDiscounts(response.data);
      });
  }, []);
  return (
    <div className="container">
      <div className="header">
        <h1 className="title">Development Books</h1>
      </div>
      <div className="content">
        {books.length === 0 && (
          <h1 className="loading-indicator">Loading...</h1>
        )}
        {books.length !== 0 && (
          <Fragment>
            <div className="products">
              {books.map((book) => {
                return (
                  <Fragment key={book.id}>
                    <Product book={book} />
                  </Fragment>
                );
              })}
            </div>
            <div className="discount-cart-panel">
              <ul className="discount">
                {Object.keys(discounts).map((distinctBooks) => (
                  <li key={distinctBooks}>
                    Buy {distinctBooks} different books and get{" "}
                    {discounts[distinctBooks]}% discount.
                  </li>
                ))}
              </ul>
              <div className="cart">
                <button className="calculate-price-btn" disabled={true}>
                  Calculate Price
                </button>
              </div>
            </div>
          </Fragment>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
