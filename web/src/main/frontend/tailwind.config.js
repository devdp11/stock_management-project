/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["../resources/templates/**/*.{html,js}"], // it will be explained later
    important: true,
    theme: {
        extend: {},
        colors: {
            'primary': '#088B2D',
            'tertiary': '#6B6B6B',
            'text-primary': '#000000',
            'text-secondary': '#ffffff',
            'info': '#3578E5',
            'background': '#F5F5F5',
            'white': '#ffffff',
            'dark': '#302F2F',
            'dark-primary': '#682288',

            'danger': '#dc3545',
            'warning': '#ffc107',
            'success': '#4BB543',

            'shadow': 'rgba(75,75,75,0.5)'
        }
    },
    plugins: [],
}