import { render } from "@testing-library/react";
import Dashboard from "../../screen/Dashboard";

describe("load development books dashboard", () => {
  test("should display development dashboard title", () => {
    const { container } = render(<Dashboard />);

    expect(container.querySelector(".title")).toContainHTML(
      "Development Books"
    );
  });
});
