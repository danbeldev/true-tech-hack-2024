import React, { FC, useEffect, useState } from 'react';

import { Card } from 'react-bootstrap';

import { EventPersonDto } from '../../../api/api.ts';

interface Props {
  persons: EventPersonDto[];
}
const PersonDisplay: FC<Props> = ({ persons }) => {
  return (
    <div className="tw-w-[90%] tw-mt-5">
      <h3>Персоны ({persons?.length})</h3>
      <div className="tw-flex tw-overflow-x-auto tw-mt-5 tw-justify-center">
        <div className={`${persons?.length > 4 ? 'tw-grid tw-grid-cols-4' : 'tw-flex'} tw-gap-2`}>
          {persons?.map((person) => (
            <Card key={person.person.id}>
              <Card.Img src={person.person.photo} />
              <Card.Body>
                <Card.Title>
                  {person.person.firstName} {person.person.lastName}
                </Card.Title>
                <Card.Subtitle>{person.post.name}</Card.Subtitle>
              </Card.Body>
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
};

export default PersonDisplay;
