let dataAPI;

const loadTable = (data) => {
  const dataLoad = document.querySelector(".data-load");
  const customers = [...data].slice(0, 11);

  customers.forEach((customer) => {
    const row = `
    <tr class="data-item">
      <td><input class="cb" type="checkbox" /></td>
      <td>${customer.name_customer}</td>
      <td>${customer.cust_number}</td>
      <td>${parseInt(customer.invoice_id)}</td>
      <td>${customer.total_open_amount}</td>
      <td>${customer.due_in_date}</td>
      <td class='predicted-date'>
        <span class='predicted-date-value'>${customer.clear_date}</span>
        <span class='predicted-date-placeholder'>-</span>
      </td>
      <td class="notes">""</td>
    </tr>
    `;
    dataLoad.innerHTML += row;
  });

  const checkboxHeader = document.querySelector(".cb-header");
  const predictBtn = document.querySelector(".predict-btn");

  const dataList = document.querySelectorAll(".data-item");
  const checkboxes = document.querySelectorAll(".cb");
  const predictValueText = document.querySelectorAll(".predicted-date-value");
  const predictValuePlaceholder = document.querySelectorAll(
    ".predicted-date-placeholder"
  );

  predictBtn.addEventListener("click", () => {
    predictValueText.forEach((text, index) => {
      text.style.display = "block";
      //console.log(dataAPI);
      predictValuePlaceholder[index].style.display = "none";
    });
  });

  checkboxes.forEach((checkBox, index) =>
    checkBox.addEventListener("click", () =>
      dataList[index].classList.toggle("selected")
    )
  );

  checkboxHeader.addEventListener("click", () => {
    dataList.forEach((data, index) => {
      if (checkboxHeader.checked) {
        if (!data.classList.contains("selected")) {
          data.classList.add("selected");
          checkboxes[index].checked = true;
        }
      } else {
        if (data.classList.contains("selected")) {
          data.classList.remove("selected");
          checkboxes[index].checked = false;
        }
      }
    });
  });
};

(function () {
  fetch("http://localhost:8080/H2HBABBA1062/fetch")
    .then((res) => res.json())
    .then((dataJSON) => {
      dataAPI = dataJSON;
      return loadTable(dataJSON);
    })
    .catch((err) => console.log(err));
})();

const searchFunction = document.getElementById("myInput");

searchFunction.addEventListener("keyup", (e) => {
  let filter = e.target.value;
  const dataLoad = document.querySelector(".data-load");

  dataLoad.innerHTML = "";

  dataAPI
    .slice(0, 11)
    .filter((data) => (data.invoice_id + "").includes(filter))
    .forEach((customer) => {
      const row = `
    <tr class="data-item">
      <td><input class="cb" type="checkbox" /></td>
      <td>${customer.name_customer}</td>
      <td>${customer.cust_number}</td>
      <td>${parseInt(customer.invoice_id)}</td>
      <td>${customer.total_open_amount}</td>
      <td>${customer.due_in_date}</td>
      <td class='predicted-date'>
        <span class='predicted-date-value'>${customer.clear_date}</span>
        <span class='predicted-date-placeholder'>-</span>
      </td>
      <td class="notes">""</td>
    </tr>
    `;
      dataLoad.innerHTML += row;
    });
});
