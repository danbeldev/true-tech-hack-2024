import React, { createContext, FC, useContext } from 'react';

const VenueMarksStore = {} as Record<number, ymaps.Map>;

export const VenueMarksContext = createContext({ venueMarks: VenueMarksStore });

const VenueMarksProvider: FC<React.PropsWithChildren> = ({ children }) => {
  return (
    <VenueMarksContext.Provider value={{ venueMarks: VenueMarksStore }}>
      {children}
    </VenueMarksContext.Provider>
  );
};
export const useVenueMarks = () => useContext(VenueMarksContext);

export default VenueMarksProvider;
