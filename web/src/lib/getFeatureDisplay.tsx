import React from 'react';

import { FaBlind, FaWheelchair } from 'react-icons/fa';
import { IoAccessibility } from 'react-icons/io5';

import { VenueDto } from '../api/api.ts';

export const getFeatureDisplay = (venue: VenueDto, size = 48) => {
  const elements: React.ReactNode[] = [];

  if (venue.features.length === 0) {
    elements.push(
      <IoAccessibility
        title={'Площадка не оборудована для инвалидов'}
        key={'NORMAL'}
        size={size}
        style={{ border: '1px solid black', padding: '4px', borderRadius: size / 3 }}
      />
    );
    return elements;
  }

  venue.features.forEach((f) => {
    switch (f) {
      case 'BLIND':
        elements.push(
          <FaBlind
            title={'Данная площадка оборудована для незрячих слушателей'}
            key={'BLIND'}
            size={size}
            style={{ border: '1px solid black', padding: '4px', borderRadius: size / 3 }}
          />
        );
        break;
      case 'WHEELCHAIR':
        elements.push(
          <FaWheelchair
            title={'Данная площадка оборудована для маломобильных слушателей'}
            key={'WHEELCHAIR'}
            size={size}
            style={{ border: '1px solid black', padding: '4px', borderRadius: size / 3 }}
          />
        );
        break;
    }
  });
  return elements;
};
