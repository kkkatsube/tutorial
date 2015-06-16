package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Task
import views._

class Application extends Controller {

  def index = Action {
    Ok("This is test page.")
  }
  
  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }
  
  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }
  
  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }
  
  val taskForm = Form(
    "label" -> nonEmptyText
  )
}
