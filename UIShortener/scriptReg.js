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
    
            fetch('http://localhost:8082/accounts/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Регистрация выполнена успешно!');
                        window.location.href = 'auth.html';
                    } else {
                        throw new Error('Ошибка');
                    }
                })
                .catch(error => {
                    errorMessage.textContent = 'Ошибка при регистрации';
                    console.error('Error:', error);
                });
        });
    });