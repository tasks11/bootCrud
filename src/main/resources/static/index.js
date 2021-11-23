document.addEventListener("DOMContentLoaded", function () {

    fetch(requestURL).then(response => response.json()).then(createTableByUser)
});

//Вывод
const usersCollection = document.getElementById('dataInf');
const requestURL = 'http://localhost:8080/clients';

const oldUserName = document.getElementById('usernameEdit');
const oldLastName = document.getElementById('lastnameEdit');
const oldUserPassword = document.getElementById('passwordEdit');
const oldEmail = document.getElementById('emailEdit');
const oldUserAge = document.getElementById('ageEdit');
const oldUserIdRole = document.getElementById('oldIdRole');

const addUserName = document.getElementById('username-value');
const addLastName = document.getElementById('lastname-value');
const addUserPassword = document.getElementById('password-value');
const addEmail = document.getElementById('email-value');
const addUserAge = document.getElementById('age-value');
const addUserIdRole = document.getElementById('idAddRole');

const btnSubmit = document.querySelector('.btn-add');
const btnUpdate = document.querySelector('.btn-update');
const btnDelete = document.querySelector('.btn-delete');

// Вывести пользователей
function createTableByUser(users) {
    usersCollection.innerHTML = '';
    let roleName = '';
    users.forEach((user) => {
        user.authorities.forEach((element) => {
            roleName = element.role;
        })
        usersCollection.innerHTML += `

     <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.lastname}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${roleName}</td>
        <td><a href="#" class="btn btn-info" id="editButton" data-id="${user.id}">Edit</a></td>
        <td><a href="#" class="btn btn-danger" id="deleteButton" data-id="${user.id}">Delete</a></td>
    </tr>
        `
    })
}

usersCollection.addEventListener('click', (e) => {
    e.preventDefault();

    let delButtIsPress = e.target.id === 'deleteButton';
    let editButtIsPress = e.target.id === 'editButton';

    //редактирование
    if (editButtIsPress) {
        $('#editModal').modal();
        let id = e.target.dataset.id;
        // let elId = document.getElementById('idEdit');
        // let id = elId.value;
        $.get(`${requestURL}/${id}`, function (user) {
            $('.editForm #idEdit').val(user.id);
            $('.editForm #usernameEdit').val(user.username);
            $('.editForm #lastnameEdit').val(user.lastname);
            $('.editForm #ageEdit').val(user.age);
            $('.editForm #passwordEdit').val(user.password);
            $('.editForm #emailEdit').val(user.email)
        })
    }
    //удаление
    if (delButtIsPress) {
        $('#deleteModal').modal();
        let id = e.target.dataset.id;
        console.log(id)
        $.get(`${requestURL}/${id}`, function (user) {
            let roleNameDel = '';
            user.authorities.forEach((element) => {
                roleNameDel = element.role;
            })

            $('.deleteForm #idDelete').val(user.id);
            $('.deleteForm #usernameDelete').val(user.username);
            $('.deleteForm #lastnameDelete').val(user.lastname);
            $('.deleteForm #ageDelete').val(user.age);
            $('.deleteForm #passwordDelete').val(user.password);
            $('.deleteForm #emailDelete').val(user.email)
            $('.deleteForm #roleDelete').val(roleNameDel);
        });
    }
})

//Post
btnSubmit.addEventListener('click', (e) => {
    e.preventDefault();
    fetch(requestURL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify({
            username: addUserName.value,
            lastname: addLastName.value,
            password: addUserPassword.value,
            email: addEmail.value,
            age: addUserAge.value,
            role: addUserIdRole.value
        })
    })
        .then(response => {
            console.log('Response:', response)
            return response.text();
        })

        .then(() => location.reload())
})

//Put
$(document).ready(function () {
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        let elId = document.getElementById('idEdit');
        let id = elId.value;
        // let roles = document.getElementById('oldIdRole');
        // let role = roles.value;
        // console.log(id)

        fetch(`${requestURL}/${id}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({
                username: oldUserName.value,
                lastname: oldLastName.value,
                password: oldUserPassword.value,
                email: oldEmail.value,
                age: oldUserAge.value,
                role: oldUserIdRole.value
            })

        })

            .then(response => {
                console.log('Response:', response)
                return response.text();
            })
            .then(getUsers)
        $('#editModal').modal('toggle')
    })

});

//Del
$(document).ready(function () {
    btnDelete.addEventListener('click', (e) => {
        e.preventDefault();
        let elId = document.getElementById('idDelete');
        let id = elId.value;
        fetch(`${requestURL}/${id}`, {
            method: 'DELETE',
        })


            .then(res => res.text())
            .then(() => location.reload())
        $('#deleteModal').modal('toggle')
    })
});

function getUsers() {
    fetch(requestURL).then(res => res.json()).then(createTableByUser);
}


