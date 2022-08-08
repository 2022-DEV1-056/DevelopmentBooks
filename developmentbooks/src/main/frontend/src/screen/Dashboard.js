import React, { useEffect } from "react";
import "../styles/dashboard.css";
import axios from "axios";

const Dashboard = () => {
  useEffect(() => {
    axios.get("http://localhost:8080/api/developmentbooks/getBooks");
  }, []);
  return (
    <div className="container">
      <div className="header">
        <h1 className="title">Development Books</h1>
      </div>
    </div>
  );
};

export default Dashboard;
