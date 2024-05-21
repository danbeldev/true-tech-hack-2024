import React, { FC } from 'react';

import { Carousel, Image } from 'react-bootstrap';

interface Props {
  images: string[];
}
const ImageDisplay: FC<Props> = ({ images }) => {
  return (
    <Carousel className="">
      {images.map((image, index) => (
        <Carousel.Item key={index}>
          <Image className="tw-max-h-[100%] tw-max-w-[100%]" src={image} />
        </Carousel.Item>
      ))}
    </Carousel>
  );
};

export default ImageDisplay;
