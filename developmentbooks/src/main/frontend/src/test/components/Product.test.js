import { render, within } from "@testing-library/react";
import Product from "../../components/Product";

const setUp = () => {
  const book = {
    id: 1,
    title: "Clean Code",
    author: "Robert Martin",
    year: 2008,
    price: 50.0,
    imageUrl:
      "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCode.png?raw=true",
  };

  return render(<Product book={book} />);
};

describe("Product component", () => {
  test("should display product information", () => {
    const { container } = setUp();

    expect(container.querySelector(".product-image")).toBeVisible();
    expect(container.querySelector(".product-title")).toBeVisible();
    expect(container.querySelector(".author")).toBeVisible();
    expect(container.querySelector(".product-price")).toBeVisible();
  });

  test("should display product price in euro format", () => {
    const { container } = setUp();

    const { getByText } = within(container.querySelector(".product-price"));
    expect(getByText("50,00 €")).toBeInTheDocument();
  });
});
