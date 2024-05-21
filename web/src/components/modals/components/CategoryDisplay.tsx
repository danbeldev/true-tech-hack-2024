import React, { FC } from 'react';

import { Card } from 'react-bootstrap';

import { CategoryDto } from '../../../api/api.ts';

interface Props {
  categories: CategoryDto[];
}
const CategoryDisplay: FC<Props> = ({ categories }) => {
  return (
    <div className="tw-flex tw-overflow-x-auto tw-mt-5 tw-justify-center tw-w-[90%] tw-gap-2">
      {categories.map((category) => (
        <Card key={category.id} className="tw-p-3">
          {category.name}
        </Card>
      ))}
    </div>
  );
};

export default CategoryDisplay;
