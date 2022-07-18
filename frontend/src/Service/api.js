import axios from "axios";

export const apiCategoria = axios.create({
  //baseUrl: "https://hospedagem-api.herokuapp.com/categorias",
  baseUrl: "http://18.222.220.168:8080"
});

export const createSession = async (email, password) => {
  return apiCategoria.post("/api/session", { email, password });
};
