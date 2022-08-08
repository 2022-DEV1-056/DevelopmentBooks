import React, { useEffect, useState, Fragment } from "react";
import "../styles/dashboard.css";
import axios from "axios";
import Product from "../components/Product";

const Dashboard = () => {
  const [books, setBooks] = useState([]);
  const [discounts, setDiscounts] = useState({});
  const [cart, setCart] = useState([]);
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);

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

  const addToCart = (id, value) => {
    const check_index = cart.findIndex((item) => item.id === id);
    if (check_index !== -1) {
      if (value === 0) {
        cart.splice(check_index, 1);
      } else {
        cart[check_index].quantity = value;
      }
    } else {
      cart.push({ id: id, quantity: value });
    }

    updateButtonStatus(cart);
    setCart(cart);
  };

  const updateButtonStatus = (cart) => {
    setIsButtonDisabled(cart.length === 0);
  };

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
                    <Product book={book} addToCart={addToCart} />
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
                <button
                  className="calculate-price-btn"
                  disabled={isButtonDisabled}
                >
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
