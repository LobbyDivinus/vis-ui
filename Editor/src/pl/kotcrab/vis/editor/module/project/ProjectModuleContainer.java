/**
 * Copyright 2014 Pawel Pastuszak
 * 
 * This file is part of VisEditor.
 * 
 * VisEditor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * VisEditor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with VisEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.kotcrab.vis.editor.module.project;

import pl.kotcrab.vis.editor.module.Module;
import pl.kotcrab.vis.editor.module.ModuleContainer;

public class ProjectModuleContainer extends ModuleContainer {
	private ModuleContainer editorModuleContainer;
	private Project project;

	public ProjectModuleContainer (ModuleContainer editorModuleContainter) {
		this.editorModuleContainer = editorModuleContainter;
	}

	@Override
	public void add (Module module) {
		if (module instanceof ProjectModule == false)
			throw new IllegalStateException("Module must be instance of Project Module!");
		if (project == null) throw new IllegalStateException("Module cannot be added before project has been set!");

		((ProjectModule)module).setProject(project);
		((ProjectModule)module).setProjectModuleContainter(this);
		super.add(module);
		module.setContainer(editorModuleContainer);
	}

	public void setProject (Project project) {
		if (getModuleCounter() > 0) throw new IllegalStateException("Project can't be changed while modules are loaded!");
		this.project = project;
	}

	public Project getProject () {
		return project;
	}
}
