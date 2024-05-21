import React, { FC } from 'react';

import { Card } from 'react-bootstrap';

import { VenueDto } from '../api/api.ts';
import { getFeatureDisplay } from '../lib/getFeatureDisplay.tsx';

const VenueDisplay: FC<{ venue: VenueDto; onClick: () => void }> = ({ venue, onClick }) => {
  return (
    <Card onClick={onClick} className="tw-cursor-pointer hover:tw-opacity-90">
      <Card.Body>
        <Card.Title>{venue.name}</Card.Title>
        <Card.Text>
          <div className="tw-flex tw-flex-row tw-content-between tw-gap-2">
            {getFeatureDisplay(venue)}
          </div>
        </Card.Text>
      </Card.Body>
    </Card>
  );
};

export default VenueDisplay;
