import { useState, createContext, useEffect} from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import dataTest from '../data/dataTest'

export const SearchContext = createContext();

export const SearchProvider = ({ children }) => {

    const [data, setData] = useState([]);

    // dados da pesquisa
    const [search, setSearch] = useState('');

    const [categoria, setCategoria ] = useState('');

    const [date, setDate] = useState([]);
    //

    const MySwal = withReactContent(Swal);


    const handleCategoria = (e) => {
      setCategoria(e.target.value);
      console.log(categoria)
    }

    const handleDate = (value) => {
      let selectedDateStart = {
        day: value[0].getDate(),
        month: value[0].getMonth()+1,
        year: value[0].getFullYear()
      }
      let selectedDateEnd = {
        day: value[1].getDate(),
        month: value[1].getMonth()+1,
        year: value[1].getFullYear()
      }
      setDate(selectedDateStart);
      console.log(date)
    }
  
    const alertConfig = (msg) => {
        MySwal.fire({
          icon: "error",
          title: msg,
        });
      };

      const getApiData = () => {
        setData(dataTest);
        console.log(data);
      };
    
      const handleSearch = (e) => {
        setSearch(e.target.value.toLowerCase());
      }
    
      const handleOnSubmit = (event) => {
        let results;
        const selectedCategory = categoria;
        const dados = dataTest;
        console.log(categoria)
        console.log(search)
        event.preventDefault();
        if ( search && categoria ) {
          results = dados.filter((item) =>
          item.title.toLowerCase().includes(search) && item.category === selectedCategory
          );
          setData(results);
        }

        else if ( !search ) {
            results = dados.filter((item) =>
            item.category === selectedCategory
          );
          setData(results);
          }
        
        else if ( !categoria ) {
            results = dados.filter((item) => item.title.toLowerCase().includes(search));
            setData(results);
        }
        else{
          event.preventDefault();
          alertConfig("Ops! Você precisa buscar um lugar ou uma categoria! :(");
        }

      if (results.length === 0) {
        alertConfig("Não há lugares disponíveis :(");
        getApiData();
      }
    }

      return (
        <SearchContext.Provider
            value={{ categoria, data, search, handleSearch, handleOnSubmit,
            handleCategoria,  getApiData, alertConfig, handleDate, date, setDate}} >
            {children}
        </SearchContext.Provider>
      )
}