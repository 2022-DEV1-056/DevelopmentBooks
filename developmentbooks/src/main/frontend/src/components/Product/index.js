import React from "react";
import PropTypes from "prop-types";
import { QuantityPicker } from "react-qty-picker";

const Product = (props) => {
  const { title, author, imageUrl, price, id, year } = props.book;

  const getFormattedPrice = (price) => {
    return new Intl.NumberFormat("de-DE", {
      style: "currency",
      currency: "EUR",
    }).format(price);
  };

  return (
    <div className="product" key={id}>
      <div className="product-image">
        <img src={imageUrl} alt={title} />
      </div>
      <p className="product-title">{title}</p>
      <p className="author">
        By {author} ({year})
      </p>
      <p className="product-price">{getFormattedPrice(price)}</p>
      <span className="product-quantity">
        <QuantityPicker
          min={0}
          smooth
          name="quantity"
          onChange={(value) => props.addToCart(id, value)}
        />
      </span>
    </div>
  );
};

Product.propTypes = {
  book: PropTypes.object.isRequired,
  addToCart: PropTypes.func.isRequired,
};

export default Product;
