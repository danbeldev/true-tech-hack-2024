import { createSlice, PayloadAction } from '@reduxjs/toolkit';

const initialState = { venueMarks: {} as Record<number, ymaps.Map> };

export const VenueMarksSlice = createSlice({
  name: 'balloonCollectionSlice',
  initialState,
  reducers: {
    setMark: (state, action: PayloadAction<{ id: number; mark: ymaps.Map }>) => {
      state.venueMarks[action.payload.id] = action.payload.mark;
    },
    clearMarks: (state) => {
      state.venueMarks = {} as Record<number, ymaps.Map>;
    },
  },
});

export const { setMark, clearMarks } = VenueMarksSlice.actions;

export default VenueMarksSlice.reducer;
