import { render } from "@testing-library/react";
import Product from "../../components/Product";

describe("Product component", () => {
  test("should display product information", () => {
    const book = {
      id: 1,
      title: "Clean Code",
      author: "Robert Martin",
      year: 2008,
      price: 50.0,
      imageUrl:
        "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCode.png?raw=true",
    };

    const { container } = render(<Product book={book} />);

    expect(container.querySelector(".product-image")).toBeVisible();
    expect(container.querySelector(".product-title")).toBeVisible();
    expect(container.querySelector(".author")).toBeVisible();
    expect(container.querySelector(".product-price")).toBeVisible();
  });
});
