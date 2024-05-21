import React, { FC, useEffect, useState } from 'react';

import { Carousel, Image, Modal, ModalProps } from 'react-bootstrap';

import CategoryDisplay from './components/CategoryDisplay.tsx';
import DescriptionDisplay from './components/DescriptionDisplay.tsx';
import ImageDisplay from './components/ImageDisplay.tsx';
import PersonDisplay from './components/PersonDisplay.tsx';
import ScheduleDisplay from './components/ScheduleDisplay.tsx';
import { api, EventDetailsDto, EventDto } from '../../api/api.ts';
import { useAppSelector } from '../../store/hooks.ts';

interface Props extends ModalProps {}
const EventModal: FC<Props> = ({ ...props }) => {
  const modalId = useAppSelector((s) => s.ModalIdSlice.modalId);
  const [event, setEvent] = useState<EventDetailsDto>();
  const getEvent = async () => {
    try {
      const response = await api.events.getById(modalId!);
      setEvent(response.data);
    } catch (e) {
      alert('Произошла внутренняя ошибка');
    }
  };
  useEffect(() => {
    if (modalId) {
      getEvent();
    }
  }, [modalId]);
  return (
    <Modal centered size={'lg'} {...props}>
      {event && (
        <>
          <Modal.Header closeButton>
            <Modal.Title>{event.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="tw-flex tw-flex-col tw-items-center tw-justify-center">
              <ImageDisplay images={event.images} />
              <CategoryDisplay categories={event.categories} />
              <ScheduleDisplay schedules={event.schedules} />
              <DescriptionDisplay description={event.description} />
              <PersonDisplay persons={event.persons} />
            </div>
          </Modal.Body>
        </>
      )}
    </Modal>
  );
};

export default EventModal;
