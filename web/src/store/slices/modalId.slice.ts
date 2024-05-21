import { createSlice, PayloadAction } from '@reduxjs/toolkit';

const initialState = { modalId: undefined as number | undefined };

export const ModalIdSlice = createSlice({
  name: 'modalIdSlice',
  initialState,
  reducers: {
    setModalId: (state, action: PayloadAction<number>) => {
      state.modalId = action.payload;
    },
    clearModalId: (state) => {
      state.modalId = undefined;
    },
  },
});

export const { setModalId, clearModalId } = ModalIdSlice.actions;

export default ModalIdSlice.reducer;
