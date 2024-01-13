package ru.betrayal.messenger.services;

import ru.betrayal.messenger.dtos.roles.CreateRoleDto;
import ru.betrayal.messenger.dtos.roles.RoleResponseDto;
import ru.betrayal.messenger.dtos.roles.UpdateRoleDto;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface RoleService extends
        DataReader<RoleResponseDto, Short>,
        DataCreator<CreateRoleDto, RoleResponseDto>,
        DataUpdated<UpdateRoleDto, RoleResponseDto>,
        DataRemover<Short> {

}
