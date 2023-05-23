import React, { useState } from 'react';
import Select from 'react-select';

const cityOptions = [
    { value: '0', label: '' },
    { value: '1', label: 'Вінниця' },
    { value: '2', label: 'Луцьк' },
    { value: '3', label: 'Дніпропетровськ' },
    { value: '4', label: 'Житомир' },
    { value: '5', label: 'Ужгород' },
    { value: '6', label: 'Запоріжжя' },
    { value: '7', label: 'Івано-Франківськ' },
    { value: '8', label: 'Кропивницький' },
    { value: '9', label: 'Луганськ' },
    { value: '10', label: 'Львів' },
    { value: '11', label: 'Миколаїв' },
    { value: '12', label: 'Одеса' },
    { value: '13', label: 'Полтава' },
    { value: '14', label: 'Рівне' },
    { value: '15', label: 'Суми' },
    { value: '16', label: 'Тернопіль' },
    { value: '17', label: 'Ужгород' },
    { value: '18', label: 'Харків' },
    { value: '19', label: 'Херсон' },
    { value: '20', label: 'Хмельницький' },
    { value: '21', label: 'Черкаси' },
    { value: '22', label: 'Чернігів' },
    { value: '23', label: 'Чернівці' },
    ];


  const customStyles = {
    container: (provided) => ({
      ...provided,
      width: '300px', // Ширина контейнера Select
    }),
    control: (provided, state) => ({
      ...provided,
      border: 'none', // Удаление рамки
      borderBottom: '2px solid #ccc', // Добавление нижней границы
      fontSize: '24px', // Размер шрифта
      padding: '10px', // Отступы
      marginRight: '20px', // Отступ справа
    }),
    option: (provided, state) => ({
      ...provided,
      background: state.isFocused ? 'lightblue' : 'white', // Фон активного или выбранного пункта списка
      color: state.isFocused ? 'white' : 'black', // Цвет текста активного или выбранного пункта списка
    }),
  };


const Search = ({ onSelectCity }) => {
    const [selectedCity, setSelectedCity] = useState(null);

    const handleCityChange = (selectedOption) => {
        setSelectedCity(selectedOption);
        onSelectCity(selectedOption);
    };

    return (
        <div>
            <Select
                value={selectedCity}
                onChange={handleCityChange}
                options={cityOptions}
                placeholder="Оберіть місто..."
                styles={customStyles}
            />
        </div>
    );
};

export {Search};