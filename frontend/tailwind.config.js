/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    colors: {
      header: '#e9dcc6',
      background: '#f0e7d8',
      black: '#000000',
    },
    extend: {
      fontFamily: {
        crayon: ['Permanent Marker', 'cursive'],
      },
    },
  },
  plugins: [],
}
