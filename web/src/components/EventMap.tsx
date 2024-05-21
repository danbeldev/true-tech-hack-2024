/* eslint-disable react/jsx-key */
import React, { FC, HTMLProps, useEffect, useState } from 'react';
import ReactDOMServer from 'react-dom/server';

import { Map } from '@pbe/react-yandex-maps';
import { YMapsApi } from '@pbe/react-yandex-maps/typings/util/typing';
import { MapProps } from '@vis.gl/react-google-maps';

import VenuePlacemark from './maps/VenuePlacemark.tsx';
import { VenueDto } from '../api/api.ts';

interface Props extends HTMLProps<unknown> {
  points: VenueDto[];
}
const EventMap: FC<Props> = ({ points, className }) => {
  const defaultState = {
    center: [55.751574, 37.573856],
    zoom: 13,
    controls: [],
  };

  const [ymaps, setYmaps] = useState<YMapsApi | null>(null);

  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  //@ts-expect-error
  const MapLoad = (ymaps) => {
    setYmaps(ymaps);
  };

  return (
    <div className={className}>
      <Map
        onLoad={(y) => MapLoad(y)}
        height="100%"
        width="100%"
        defaultState={defaultState}
        modules={['templateLayoutFactory', 'layout.ImageWithContent']}
      >
        {ymaps && points?.map((p) => <VenuePlacemark venue={p} key={p.id} />)}
      </Map>
    </div>
  );
};

export default EventMap;
