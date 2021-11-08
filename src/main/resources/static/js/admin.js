async function userList() {
    let getUserList = await fetch('/api/userList')
    let userList = await getUserList.json()
    let table = document.getElementById('table')

    for (let user in userList) {
        let html = `<td>${userList[user].id}</td>
        <td>${userList[user].name}</td>
        <td>${userList[user].surName}</td>
        <td>${userList[user].age}</td>
        <td>${userList[user].email}</td>
        <td>${userList[user].password}</td><td>`
        for (let j in userList[user].roles) {
            html += userList[user].roles[j].role + ' '
        }
        table.innerHTML += html + `</td><td><button type="submit" class="btn btn-secondary btn-sm" 
            data-toggle="modal" data-target="#editModal" onclick="findUserById(this)">Изменить</button></td>
        <td><button type="submit" class="btn btn-danger btn-sm" 
            data-toggle="modal" data-target="#deleteModal" onclick="delUserById(this)">Удалить</button></td>`
    }
}

async function userInfo() {
    let getUserInfo = await fetch('/api/userInfo')
    let userInfo = await getUserInfo.json()
    let tableInfo = document.getElementById('userInfo')

    let htmlInfo = `<td>${userInfo.id}</td>
    <td>${userInfo.name}</td>
    <td>${userInfo.surName}</td>
    <td>${userInfo.age}</td>
    <td>${userInfo.email}</td>
    <td>${userInfo.password}</td><td>`
    for (let j in userInfo.roles) {
        htmlInfo += userInfo.roles[j].role + ' '
    }
    tableInfo.innerHTML += htmlInfo + '</td>'
}

userList()
userInfo()

function clearUserList() {
    let clearUsers = document.getElementById("table");
    while (clearUsers.hasChildNodes()) {
        clearUsers.removeChild(clearUsers.lastChild);
    }
}

function clearUserInfo() {
    let clearUser = document.getElementById("userInfo");
    while (clearUser.hasChildNodes()) {
        clearUser.removeChild(clearUser.lastChild);
    }
}

async function addNewUser() {
    let newUser = {
        // id: '',
        name: document.getElementById('newUserName').value,
        surName: document.getElementById('newUserSurName').value,
        age: parseInt(document.getElementById('newUserAge').value),
        email: document.getElementById('newUserEmail').value,
        password: document.getElementById('newUserPassword').value,
        roles: []
    }
    if (document.getElementById('1').checked) newUser.roles.push("ROLE_ADMIN")
    if (document.getElementById('2').checked) newUser.roles.push("ROLE_USER")

    console.log(newUser)

    await fetch('/api/add', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(newUser)
    })

    clearUserList()
    userList()
}

let userId
let userName
let userSurName
let userAge
let userEmail
let userPassword

function findUserById(id) {
    userId = id.parentNode.parentNode.cells[0].textContent
    userName = id.parentNode.parentNode.cells[1].textContent
    userSurName = id.parentNode.parentNode.cells[2].textContent
    userAge = id.parentNode.parentNode.cells[3].textContent
    userEmail = id.parentNode.parentNode.cells[4].textContent
    userPassword = id.parentNode.parentNode.cells[5].textContent

    document.getElementById('recipient-name').setAttribute('value', userName)
    document.getElementById('recipient-surname').setAttribute('value', userSurName)
    document.getElementById('recipient-age').setAttribute('value', userAge)
    document.getElementById('recipient-email').setAttribute('value', userEmail)
    document.getElementById('recipient-password').setAttribute('value', userPassword)

    console.log(userId, userName, userSurName, userAge, userEmail, userPassword)
}

async function editButton() {
    let editUser = {
        id: parseInt(userId),
        name: document.getElementById('recipient-name').value,
        surName: document.getElementById('recipient-surname').value,
        age: parseInt(document.getElementById('recipient-age').value),
        email: document.getElementById('recipient-email').value,
        password: document.getElementById('recipient-password').value,
        roles: []
    }
    if (document.getElementById('checkbox1').checked) editUser.roles.push("ROLE_ADMIN")
    if (document.getElementById('checkbox2').checked) editUser.roles.push("ROLE_USER")

    console.log(editUser)

    await fetch('/api/edit', {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(editUser)
    })
    clearUserList()
    clearUserInfo()
    userList()
    userInfo()

    console.log(userId)
}

function delUserById(id) {
    userId = id.parentNode.parentNode.cells[0].textContent
    userName = id.parentNode.parentNode.cells[1].textContent
    userSurName = id.parentNode.parentNode.cells[2].textContent
    userAge = id.parentNode.parentNode.cells[3].textContent
    userEmail = id.parentNode.parentNode.cells[4].textContent
    userPassword = id.parentNode.parentNode.cells[5].textContent

    document.getElementById('del-name').setAttribute('value', userName)
    document.getElementById('del-surname').setAttribute('value', userSurName)
    document.getElementById('del-age').setAttribute('value', userAge)
    document.getElementById('del-email').setAttribute('value', userEmail)
    document.getElementById('del-password').setAttribute('value', userPassword)

    console.log(userId, userName, userSurName, userAge, userEmail, userPassword)
}

async function deleteUser() {
    let delUser = {
        id: parseInt(userId),
    }

    await fetch('/api/delete', {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(delUser)
    })
    clearUserList()
    clearUserInfo()
    userList()
    userInfo()

}

