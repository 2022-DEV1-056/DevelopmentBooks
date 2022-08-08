import React from "react";
import PropTypes from "prop-types";

const Product = (props) => {
  const { title, author, imageUrl, price, id, year } = props.book;

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
};

Product.propTypes = {
  book: PropTypes.object.isRequired,
};

export default Product;
