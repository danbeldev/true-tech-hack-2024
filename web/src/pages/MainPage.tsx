import React, { useEffect, useState } from 'react';

import { useDispatch } from 'react-redux';

import { api, EventDetailsDto, VenueDto } from '../api/api.ts';
import EventMap from '../components/EventMap.tsx';
import EventModal from '../components/modals/EventModal.tsx';
import VenueList from '../components/VenueList.tsx';
import { useAppDispatch, useAppSelector } from '../store/hooks.ts';
import { clearModalId, setModalId } from '../store/slices/modalId.slice.ts';

const MainPage = () => {
  const [points, setPoints] = useState<VenueDto[]>();
  const dispatch = useAppDispatch();
  const get = async () => {
    const response = await api.venues.getAll();

    setPoints(response.data);
  };

  useEffect(() => {
    get();
  }, []);
  const modalId = useAppSelector((s) => s.ModalIdSlice.modalId);

  const [show, setShow] = useState(false);
  useEffect(() => {
    if (modalId) {
      setShow(true);
    }
  }, [modalId]);
  return (
    <div className="tw-h-screen tw-flex tw-w-screen">
      {points && (
        <>
          <div className="tw-w-[15%] tw-min-w-[150px] tw-h-full tw-flex tw-flex-col tw-p-4 overflow-auto">
            <VenueList points={points} />
          </div>
          <EventMap points={points} className={'tw-h-screen tw-overflow-hidden tw-flex-grow'} />
        </>
      )}
      <EventModal
        show={show}
        onHide={() => {
          dispatch(clearModalId());
          setShow(false);
        }}
      />
    </div>
  );
};

export default MainPage;
