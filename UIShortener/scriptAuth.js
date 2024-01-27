document.addEventListener('DOMContentLoaded', () => {
const authForm = document.getElementById('authForm');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const errorMessage = document.getElementById('errorMessage');

    authForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const username = usernameInput.value;
        const password = passwordInput.value;

        const data = {
            username,
            password
        };

        fetch('http://localhost:8082/accounts/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    return response.json(); // Обработка JSON-ответа
                } else {
                    throw new Error('Ошибка');
                }
            })
            .then(loginResponse => {
                // Полученные данные из JSON-ответа
                const userId = loginResponse.id;
                const username = loginResponse.username;

                // Передача данных в окно main.html
                const url = `Main.html?userId=${userId}&username=${username}`;
                window.location.href = url;
            })
            .catch(error => {
                errorMessage.textContent = 'Неверный логин или пароль';
                console.error('Error:', error);
            });
    });
});