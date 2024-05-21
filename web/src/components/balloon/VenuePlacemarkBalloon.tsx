import React, { FC, useEffect, useState } from 'react';

import { Button, Card } from 'react-bootstrap';
import { useDispatch } from 'react-redux';

import { api, EventDto, VenueDto } from '../../api/api.ts';
import { getFeatureDisplay } from '../../lib/getFeatureDisplay.tsx';
import { useAppDispatch } from '../../store/hooks.ts';
import { setModalId } from '../../store/slices/modalId.slice.ts';
import EventModal from '../modals/EventModal.tsx';

interface Props {
  venue: VenueDto;
  onClick: (id: number) => void;
}

const VenuePlacemarkBalloon: FC<Props> = ({ venue, onClick }) => {
  const [events, setEvents] = useState<EventDto[]>();
  const getEvent = async () => {
    try {
      const response = await api.events.getAll2({ venueId: venue.id, status: 'ACTUAL' });
      setEvents(response.data);
    } catch (e) {}
  };

  useEffect(() => {
    getEvent();
  }, []);
  const onWatchClick = (eventId: number) => {
    const close = document.getElementsByClassName('ymaps-2-1-79-balloon__close');
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    //@ts-expect-error
    close[0].click();
    onClick(eventId);
  };
  return (
    <Card>
      <Card.Header>
        <div className="tw-flex tw-justify-between tw-content-center">
          <span className="tw-text-center tw-text-base tw-font-bold">{venue.name}</span>
          <div className="tw-flex tw-gap-2">{getFeatureDisplay(venue, 32)}</div>
        </div>
      </Card.Header>
      <Card.Body>
        <div className="tw-flex tw-flex-col tw-gap-2">
          {events ? (
            events.map((event) => (
              <Card key={event.id}>
                <Card.Img variant="top" src={event.image} />
                <Card.Title className={'tw-p-1'}>{event.name}</Card.Title>
                <Button
                  variant="primary"
                  className="tw-content-center tw-m-2"
                  onClick={() => onWatchClick(event.id)}
                >
                  Посмотреть
                </Button>
              </Card>
            ))
          ) : (
            <Card.Img src="https://kartinki.pics/uploads/posts/2022-12/1670639655_1-kartinkin-net-p-kartinki-zagruzki-pinterest-1.png" />
          )}
        </div>
      </Card.Body>
    </Card>
  );
};

export default VenuePlacemarkBalloon;
