import React, { FC } from 'react';

interface Props {
  description: string;
}
const DescriptionDisplay: FC<Props> = ({ description }) => {
  return (
    <div className="tw-mt-5 tw-w-[90%]">
      <h3>Описание</h3>
      <div>{description}</div>
    </div>
  );
};

export default DescriptionDisplay;
