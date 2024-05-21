import React, { FC } from 'react';
import { createRoot } from 'react-dom/client';
import ReactDOMServer from 'react-dom/server';

import { Placemark, useYMaps } from '@pbe/react-yandex-maps';
import { Provider } from 'react-redux';

import { VenueDto } from '../../api/api.ts';
import { useVenueMarks } from '../../store/context/VenueMarksProvider.tsx';
import { useAppDispatch } from '../../store/hooks.ts';
import { setModalId } from '../../store/slices/modalId.slice.ts';
import { store } from '../../store/store.ts';
import VenuePlacemarkBalloon from '../balloon/VenuePlacemarkBalloon.tsx';

interface PlacemarkProps {
  venue: VenueDto;
}

const VenuePlacemark: FC<PlacemarkProps> = ({ venue }) => {
  const { venueMarks } = useVenueMarks();
  const ymaps = useYMaps();

  const dispatch = useAppDispatch();

  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  //@ts-expect-error
  const BalloonContentLayout = (layoutFactory, Component) => {
    const html = ReactDOMServer.renderToString(Component);
    const Layout = layoutFactory.createClass(`<div id="balloon">${html}</div>`, {
      build: function () {
        Layout.superclass.build.call(this);
      },
    });

    return Layout;
  };

  return (
    <>
      {ymaps && (
        <Placemark
          modules={['geoObject.addon.balloon', 'geoObject.addon.hint']}
          key={venue.id}
          geometry={[venue.latitude, venue.longitude]}
          instanceRef={(ref) => (venueMarks[venue.id] = ref)}
          onBalloonOpen={() => {
            createRoot(document.getElementById('balloon')!).render(
              <VenuePlacemarkBalloon
                venue={venue}
                onClick={(id) => {
                  dispatch(setModalId(id));
                }}
              />
            );
          }}
          options={{
            // balloonCloseButton: false,
            // Балун будем открывать и закрывать кликом по иконке метки.
            hideIconOnBalloonOpen: false,
            balloonContentLayout: BalloonContentLayout(
              ymaps.templateLayoutFactory,
              <VenuePlacemarkBalloon venue={venue} onClick={(id) => dispatch(setModalId(id))} />
            ),
          }}
        />
      )}
    </>
  );
};

export default VenuePlacemark;
