import { render } from "@testing-library/react";
import Dashboard from "../../screen/Dashboard";
import axios from "axios";

afterEach(() => {
  axios.get.mockClear();
});
jest.mock("axios");

describe("load development books dashboard", () => {
  test("should display development dashboard title", () => {
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
});
