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

userInfo()