import { render, waitFor, fireEvent } from "@testing-library/react";
import Dashboard from "../../screen/Dashboard";
import axios from "axios";
import getBooksResponse from "./responses/getBooks.json";
import getDiscountDetailsResponse from "./responses/getDiscountDetails.json";

afterEach(() => {
  axios.get.mockClear();
});
jest.mock("axios");

const setUp = () => {
  axios.get.mockImplementation((url) => {
    if (url.indexOf("/api/developmentbooks/getBooks") !== -1) {
      return Promise.resolve({
        status: 200,
        data: getBooksResponse,
      });
    }
    if (url.indexOf("/api/developmentbooks/getDiscountDetails") !== -1) {
      return Promise.resolve({
        status: 200,
        data: getDiscountDetailsResponse,
      });
    }
    return new Promise(() => {});
  });

  return render(<Dashboard />);
};

describe("load development books dashboard", () => {
  test("should display development dashboard title", () => {
    axios.get.mockImplementation(() => new Promise(() => {}));

    const { container } = render(<Dashboard />);

    expect(container.querySelector(".title")).toContainHTML(
      "Development Books"
    );
  });

  test("should call get books API", () => {
    axios.get.mockImplementation(() => new Promise(() => {}));

    render(<Dashboard />);

    expect(axios.get).toHaveBeenCalledWith(
      expect.stringMatching(/\/api\/developmentbooks\/getBooks/)
    );
  });

  test("should display books from get books API response", async () => {
    axios.get.mockImplementation((url) => {
      if (url.indexOf("/api/developmentbooks/getBooks") !== -1) {
        return Promise.resolve({
          status: 200,
          data: getBooksResponse,
        });
      }
      return new Promise(() => {});
    });

    const { container } = render(<Dashboard />);
    const products = await waitFor(() =>
      container.getElementsByClassName("product")
    );

    expect(products.length).toBe(5);
  });

  test("should display loading indicator when there is no data", () => {
    axios.get.mockImplementation(() => new Promise(() => {}));

    const { container } = render(<Dashboard />);

    expect(container.querySelector(".loading-indicator")).toHaveTextContent(
      "Loading..."
    );
  });

  describe("Discount container", () => {
    test("should call get discount details API", () => {
      axios.get.mockImplementation(() => new Promise(() => {}));

      render(<Dashboard />);

      expect(axios.get).toHaveBeenCalledWith(
        expect.stringMatching(/\/api\/developmentbooks\/getDiscountDetails/)
      );
    });

    test("should display discount details", async () => {
      const { container } = setUp();

      const discounts = await waitFor(() => {
        const discountWrapper = container.firstChild.querySelector(
          ".discount-cart-panel ul.discount"
        );
        return discountWrapper.querySelectorAll("li");
      });

      expect(discounts.length).toBe(4);
    });

    test("should display calculate price button", async () => {
      const { container } = setUp();

      await waitFor(() => container.getElementsByClassName("product"));
      expect(container.querySelector(".calculate-price-btn")).toBeVisible();
    });

    test("should display calculate price as disabled by default", async () => {
      const { container } = setUp();

      await waitFor(() => container.getElementsByClassName("product"));

      expect(container.querySelector(".calculate-price-btn")).toBeDisabled();
    });

    test("should enable calculate price button when a product is added to the cart", async () => {
      const { container } = setUp();

      const products = await waitFor(() =>
        container.getElementsByClassName("products")
      );
      fireEvent(
        products[0].querySelector(".add"),
        new MouseEvent("click", {
          bubbles: true,
          cancelable: true,
        })
      );

      expect(
        container.querySelector(".calculate-price-btn")
      ).not.toBeDisabled();
    });
  });
});
