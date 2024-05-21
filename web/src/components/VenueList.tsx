import React, { FC } from 'react';

import VenueDisplay from './VenueDisplay.tsx';
import { VenueDto } from '../api/api.ts';
import { useVenueMarks } from '../store/context/VenueMarksProvider.tsx';

interface Props {
  points: VenueDto[];
}
const VenueList: FC<Props> = ({ points }) => {
  const { venueMarks } = useVenueMarks();
  return (
    <div className="tw-gap-2 tw-flex tw-flex-col">
      {points?.map((p) => (
        <VenueDisplay
          key={p.id}
          venue={p}
          onClick={() => {
            const element = venueMarks[p.id];
            element.balloon.close();
            element.balloon.open();
          }}
        />
      ))}
    </div>
  );
};

export default VenueList;
