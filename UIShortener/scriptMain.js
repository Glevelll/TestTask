document.addEventListener('DOMContentLoaded', () => {
const urlForm = document.getElementById('urlForm');
const urlInput = document.getElementById('urlInput');
const urlTable = document.getElementById('urlTable').getElementsByTagName('tbody')[0];
const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get('userId');
const username = urlParams.get('username');

// Функция для добавления новой записи в таблицу
function addTableRow(id, originalUrl, shortUrl) {
  const row = urlTable.insertRow();
  const col1 = row.insertCell();
  const col2 = row.insertCell();
  const col3 = row.insertCell();
  const col4 = row.insertCell();

  col1.textContent = originalUrl;

  const shortUrlLink = document.createElement('a');
  shortUrlLink.href = shortUrl; 
  shortUrlLink.textContent = shortUrl;
  shortUrlLink.target = '_blank'; 
  col2.appendChild(shortUrlLink);

  shortUrlLink.addEventListener('click', (event) => {
    event.preventDefault(); // Отменить стандартное действие перехода по ссылке
    window.open(originalUrl, '_blank'); 
  });

  const editButton = document.createElement('button');
  editButton.textContent = 'Редактировать';
  editButton.addEventListener('click', () => {
    const textField = document.createElement('input');
    textField.type = 'text';
    textField.value = shortUrl.split('test.kzn/')[1];
    col2.innerHTML = '';
    col2.appendChild(textField);

    editButton.textContent = 'Ок';

    editButton.addEventListener('click', () => {
      const updatedShortUrl = `test.kzn/${textField.value}`;
      col2.innerHTML = updatedShortUrl;
      editButton.textContent = 'Редактировать';

      // Отправка запроса на сервер для обновления данных
      updateUrl(id, updatedShortUrl);
    });
  });
  col3.appendChild(editButton);

  const deleteButton = document.createElement('button');
  deleteButton.textContent = 'Удалить';
  deleteButton.addEventListener('click', () => {
    console.log('Удалить:', originalUrl, shortUrl);
    deleteUrl(id);
  });
  col4.appendChild(deleteButton);
}

function updateUrl(id, newShortUrl) {
  fetch(`http://localhost:8082/urls/updateUrl/${id}?shortUrl=${encodeURIComponent(newShortUrl)}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then((response) => {
      if (response.ok) {
        console.log(`Запись с id ${id} успешно обновлена`);
        location.reload(); // Обновить страницу
      } else {
        throw new Error(`Ошибка обновления записи с id ${id} - ${response.status}`);
      }
    })
    .catch((error) => {
      console.log('Ошибка при обновлении записи:', error);
    });
}

// Функция для получения данных и заполнения таблицы
function fetchDataAndPopulateTable() {
  fetch(`http://localhost:8082/urls/getUrlByUserId/${userId}`)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error(`Ошибка получения данных - ${response.status}`);
      }
    })
    .then((data) => {
      data.forEach((url) => {
        addTableRow(url.id, url.originalUrl, url.shortUrl);
      });
    })
    .catch((error) => {
      console.log('Ошибка при получении данных:', error);
      alert(`Ошибка при получении данных: ${error.message}`);
    });
}

// Функция для отправки запроса на удаление записи
function deleteUrl(id) {
  fetch(`http://localhost:8082/urls/deleteUrl/${id}`, {
    method: 'DELETE',
  })
    .then((response) => {
      if (response.ok) {
        console.log(`Запись с id ${id} успешно удалена`);
        location.reload();
        // Удалить строку из таблицы после успешного удаления
        const row = document.querySelector(`td:contains("${id}")`).parentNode;
        row.parentNode.removeChild(row);
        location.reload(); 
      } else {
        throw new Error(`Ошибка удаления записи с id ${id} - ${response.status}`);
      }
    })
    .catch((error) => {
      console.log('Ошибка при удалении записи:', error);
    });
}

function sendUrlDataToServer(originalUrl) {
  const data = new FormData();
  data.append('inUrl', originalUrl);
  data.append('id_user', userId);

  fetch('http://localhost:8082/urls/createUrl', {
    method: 'POST',
    body: data,
  })
    .then((response) => {
      if (response.ok) {
        return response.text();
      } else {
        throw new Error(`Ошибка отправки данных на сервер - ${response.status}`);
      }
    })
    .then((shortUrl) => {
      addTableRow(null, originalUrl, shortUrl);
      urlInput.value = '';
      location.reload();
    })
    .catch((error) => {
      console.log('Ошибка при отправке данных на сервер:', error);
      alert(`Ошибка при отправке данных на сервер: ${error.message}`);
    });
}

urlForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const originalUrl = urlInput.value;
  sendUrlDataToServer(originalUrl);
});

const exitButton = document.querySelector('.exit-button');
exitButton.addEventListener('click', () => {
  window.location.href = 'auth.html';
  console.log('Выход');
});

fetchDataAndPopulateTable();
});