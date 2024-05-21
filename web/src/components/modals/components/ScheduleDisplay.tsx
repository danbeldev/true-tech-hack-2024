import React, { FC } from 'react';

import * as dayjs from 'dayjs';
import { Card } from 'react-bootstrap';

import { EventScheduleDto } from '../../../api/api.ts';

interface Props {
  schedules: EventScheduleDto[];
}
const ScheduleDisplay: FC<Props> = ({ schedules }) => {
  return (
    <div className="tw-w-[90%]">
      <h3>Расписание ({schedules?.length})</h3>
      <div className="tw-flex tw-overflow-x-auto tw-mt-5 tw-justify-center">
        <div className="tw-flex tw-gap-2">
          {schedules.map((sc) => (
            <Card key={sc.id} className="tw-p-3">
              {new Date(sc.dateTime).toLocaleString('ru-RU', {
                day: 'numeric',
                month: 'long',
                year: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
              })}
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ScheduleDisplay;
