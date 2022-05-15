$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "EmployeesAPI",
	type : type,
	data : $("#formItem").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onEmployeeSaveComplete(response.responseText, status);
	}
	});
});
// DELETE==============================================
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "EmployeesAPI",
		type : "DELETE",
		data : "EmployeeID=" + $(this).data("EmployeeID"),
		dataType : "text",
		complete : function(response, status)
		{
		onEmployeeDeleteComplete(response.responseText, status);
		}
		});
		});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("EmployeeID"));
$("#EmployeeName").val($(this).closest("tr").find('td:eq(0)').text());
$("#EmployeeEmail").val($(this).closest("tr").find('td:eq(1)').text());
$("#EmployeeType").val($(this).closest("tr").find('td:eq(2)').text());
$("#EmployeeContact").val($(this).closest("tr").find('td:eq(3)').text());
});
// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#EmployeeName").val().trim() == "")
{
return "Insert Employee Name.";
}
// NAME
if ($("#EmployeeEmail").val().trim() == "")
{
return "Insert Employee Email.";
}
//PRICE-------------------------------
if ($("#EmployeeType").val().trim() == "")
{
return "Insert Employee Type.";
}
//// is numerical value
//var tmpPrice = $("#itemPrice").val().trim();
//if (!$.isNumeric(tmpPrice))
//{
//return "Insert a numerical value for Item Price.";
//}
// convert to decimal price
//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
// DESCRIPTION------------------------
if ($("#EmployeeContact").val().trim() == "")
{
return "Insert Employee Contact.";
}
return true;
}


var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
		{
		url : "EmployeesAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
		onEmployeeSaveComplete(response.responseText, status);
		}
		});


function onEmployeeSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidItemIDSave").val("");
$("#formItem")[0].reset();
}


function onEmployeeDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}
