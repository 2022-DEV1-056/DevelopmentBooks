import { render, waitFor } from "@testing-library/react";
import Dashboard from "../../screen/Dashboard";
import axios from "axios";
import getBooksResponse from "./responses/getBooks.json";

afterEach(() => {
  axios.get.mockClear();
});
jest.mock("axios");

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

  test("should display book information for each product", async () => {
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
    expect(products[0].querySelector(".product-image")).toBeInTheDocument;
    expect(products[0].querySelector(".product-title")).toBeInTheDocument;
    expect(products[0].querySelector(".author")).toBeInTheDocument;
    expect(products[0].querySelector(".product-price")).toBeInTheDocument;
  });
});
