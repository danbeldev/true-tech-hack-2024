import React from 'react';

import { YMaps } from '@pbe/react-yandex-maps';

import './App.css';

import { Provider } from 'react-redux';

import MainPage from './pages/MainPage.tsx';
import VenueMarksProvider from './store/context/VenueMarksProvider.tsx';
import { store } from './store/store.ts';

function App() {
  return (
    <Provider store={store}>
      <VenueMarksProvider>
        <YMaps
          query={{
            ns: 'use-load-option',
            load: 'Map,Placemark,geoObject.addon.balloon,templateLayoutFactory,layout.ImageWithContent',
            lang: 'ru_RU',
          }}
        >
          <MainPage />
        </YMaps>
      </VenueMarksProvider>
    </Provider>
  );
}

export default App;
